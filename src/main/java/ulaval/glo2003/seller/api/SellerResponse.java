package ulaval.glo2003.seller.api;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SellerResponse {
    private final String id;
    private final String name;
    private final String createdAt;
    private final String bio;

    public SellerResponse(String id, String name, String createdAt, String bio) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.bio = bio;
    }
}
