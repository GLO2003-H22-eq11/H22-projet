package ulaval.glo2003.seller.api;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SellerResponse {
    public final String id;
    public final String name;
    public final String createdAt;
    public final String bio;

    public SellerResponse(String id, String name, String createdAt, String bio) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.bio = bio;
    }
}
