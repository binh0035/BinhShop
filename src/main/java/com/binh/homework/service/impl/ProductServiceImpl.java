package com.binh.homework.service.impl;

import com.binh.homework.dao.ProductDao;
import com.binh.homework.dao.TrxDao;
import com.binh.homework.meta.*;
import com.binh.homework.service.IProductService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binh on 2017/4/15.
 */
public class ProductServiceImpl implements IProductService {

    @Override
    public List<ProductIndex> getProductIndex(HttpServletRequest request, Person person) {
        List<ProductIndex> productIndexList = new ArrayList<>();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        List<Product> productList = productDao.getProductList();

        TrxDao trxDao = context.getBean("trxDao", TrxDao.class);

        ProductIndex productIndex;
        for (Product product : productList) {
            productIndex = new ProductIndex(product);
            if (person != null && person.getUserType() == 0) { // ==0 是买家
                productIndex.setIsBuy(trxDao.getCountByContentIdPersonId(product.getId(), person.getId()) > 0);
            } else {
                productIndex.setIsSell(trxDao.getCountByContentId(product.getId()) > 0);
            }
            productIndexList.add(productIndex);
        }
        return productIndexList;
    }

    @Override
    public ProductShow getProductShow(HttpServletRequest request, Person person, int productId) {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        Product product = productDao.getProduct(productId);
        ProductShow productShow = new ProductShow(product);

        TrxDao trxDao = context.getBean("trxDao", TrxDao.class);

        if (person != null && person.getUserType() == 0) {
            List<Trx> trxList = trxDao.getTrxByContentIdPersonId(productId, person.getId());
            int buyPrice = 0;
            for (Trx trx : trxList) {
                buyPrice += trx.getPrice();
            }
            if (trxList.size() > 0) {
                buyPrice /= trxList.size();
            }
            productShow.setBuyPrice(buyPrice);      // 取购买价格的平均值
            productShow.setBuyNum(trxDao.getCountByContentIdPersonId(productId, person.getId()));
            productShow.setIsBuy(productShow.getBuyNum() > 0);
        } else {
            productShow.setSaleNum(trxDao.getCountByContentId(productId));
            productShow.setIsSell(productShow.getSaleNum() > 0);
        }

        return productShow;
    }

    @Override
    public List<ProductAccount> getProductAccount(HttpServletRequest request, Person person) {
        List<ProductAccount> productAccountList = new ArrayList<>();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        TrxDao trxDao = context.getBean("trxDao", TrxDao.class);

        ProductAccount productAccount;
        Product product;
        if (person != null && person.getUserType() == 0) {
            List<Trx> trxList = trxDao.getTrxByPersonId(person.getId());
            int buyNum = 0;
            Trx preTrx = null;
            Trx trx;
            for (int i = 0; i <= trxList.size(); i++) {
                if (i != trxList.size()) {
                    trx = trxList.get(i);
                } else {
                    trx = null;
                }
                if (trx != null && (trx.equals(preTrx) || buyNum == 0)) {
                    buyNum++;
                } else if (preTrx != null){
                    product = productDao.getProduct(preTrx.getContentId());
                    productAccount = new ProductAccount(product);
                    productAccount.setBuyPrice(preTrx.getPrice());
                    productAccount.setBuyNum(buyNum);
                    productAccount.setBuyTime(preTrx.getTime());
                    productAccount.setTotal(preTrx.getPrice() * buyNum);

                    buyNum = 0;
                    productAccountList.add(productAccount);
                }
                preTrx = trx;
            }
        }
        return productAccountList;
    }
}
