package com.example.eanov2022ioc;

@MyBean
public class CategoryController {
    @MyAutowired
    public ProductController productController;

    public void print() {
        System.out.println("Print in Category Class");
    }

    public void display() {
        productController.print();
    }
}
