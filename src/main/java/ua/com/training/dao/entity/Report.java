package ua.com.training.dao.entity;

public class Report {

    private Long userId;
    private Integer checksCount;
    private Integer quantity;
    private Double bill;

    private Report(ReportBuilder reportBuilder) {
        this.userId = reportBuilder.userId;
        this.checksCount = reportBuilder.checksCount;
        this.quantity = reportBuilder.quantity;
        this.bill = reportBuilder.bill;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getChecksCount() {
        return checksCount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getBill() {
        return bill;
    }


    public static class ReportBuilder {

        private Long userId;
        private Integer checksCount;
        private Integer quantity;
        private Double bill;

        public ReportBuilder() {
        }

        public ReportBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ReportBuilder checksCount(Integer checksCount) {
            this.checksCount = checksCount;
            return this;
        }

        public ReportBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ReportBuilder bill(Double bill) {
            this.bill = bill;
            return this;
        }

        public Report build() {
            Report report = new Report(this);
            return report;
        }
    }
}
