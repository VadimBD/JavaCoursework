package org.example.onlinestore.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.onlinestore.entity.Brand;
import org.example.onlinestore.entity.Product;
import org.example.onlinestore.entity.ProductContainer;
import org.example.onlinestore.entity.Review;
import org.example.onlinestore.repository.BrandRepository;
import org.example.onlinestore.repository.ProductContainerRepository;
import org.example.onlinestore.repository.ProductRepository;
import org.example.onlinestore.repository.ReviewRepository;
import org.example.onlinestore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service

@NoArgsConstructor
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductContainerRepository productContainerRepository;

    private Utils utils;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProduct(int id) {
        var p=productRepository.findById(id);
         return p!=null?p.get():null;
    }

    public List<ProductContainer> getAllProductContainers() {
        return productContainerRepository.findAll();
    }
    public ProductContainer getProductContainer(int id) {
        return productContainerRepository.findById(id).get();
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    public Review getReview(int id) {
        return reviewRepository.findById(id).get();
    }

    public  List<Product> getProducts(Integer brandId) {
        return  productRepository.findProductByBrandId(brandId);
    }
    //region TopSales
    public List<Product> getTopSales() {
        return productRepository.findByTopSalesTrue();
    }
    public  List<Product> getTopSales (int count) {
        return productRepository.findByNoveltyIsTrue(PageRequest.of(0, count));
    }
    public  List<Product> getTopSalesRnd (int count){
        return getRandomProducts(getTopSales(), count);
    }
    //endregion

    //region Novelties
    public  List<Product> getNovelties() {
        return  productRepository.findByNoveltyIsTrue();
    }
    public List<Product> getNovelties(int count) {
        return productRepository.findByNoveltyIsTrue(PageRequest.of(0, count));
    }
    public  List<Product> getNoveltiesRnd (int count){
        return getRandomProducts(getNovelties(), count);
    }
    //endregion
    public Brand getBrand(Integer brandId) {
        return brandRepository.findById(brandId).get();
    }
    public  List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    private List<Product> getRandomProducts( List<Product>  list , int count ) {
        var copy = new ArrayList<Product>(list);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(count, copy.size()));
    }

    public  void addReview(String text, int productId, UUID userId){
        var review = new Review();
        var time=OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        review.setProduct(getProduct(productId));
        review.setReviewText(text);
        review.setUserId(userId);
        review.setIsertedDate(time);
        reviewRepository.save(review);
    }



}
