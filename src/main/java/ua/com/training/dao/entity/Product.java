package ua.com.training.dao.entity;

public class Product {

    private Long id;
    private String code;
    private String name;
    private Size size;
    private Double price;
    private Integer quantity;
    private Double bill;

    private Product(ProductBuilder productBuilder) {
        this.id = productBuilder.id;
        this.code = productBuilder.code;
        this.name = productBuilder.name;
        this.size = productBuilder.size;
        this.price = productBuilder.price;
        this.quantity = productBuilder.quantity;
        this.bill = productBuilder.bill;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Size getSize() {
        return size;
    }

    public Double getBill() {
        return bill;
    }

    public static class ProductBuilder {

        private Long id;
        private String code;
        private String name;
        private Double price;
        private Integer quantity;
        private Size size;
        private Double bill;

        public ProductBuilder() {
        }

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder code(String code) {
            this.code = code;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder size(Size size) {
            this.size = size;
            return this;
        }

        public ProductBuilder bill(Double bill) {
            this.bill = bill;
            return this;
        }

        public Product build() {
            Product product = new Product(this);
            return product;
        }
    }
}
