package models;
import lombok.Data;
@Data
public class LombokModel {
    private Data data;
    private SupportModel support;
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;


    @lombok.Data
    public static class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }
    @lombok.Data
    public static class SupportModel {
        private String url;
        private String text;
    }
}