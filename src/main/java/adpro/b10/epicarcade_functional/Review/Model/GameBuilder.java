package adpro.b10.epicarcade_functional.Review.Model;

import java.util.UUID;

public class GameBuilder {
    private String id;
    private String name;
    private String description;
    private int price;
    private int stock;

    public GameBuilder id(String id) {
        this.id = id;
        return this;
    }

    public GameBuilder name(String name) {
        this.name = name;
        return this;
    }

    public GameBuilder description(String description) {
        this.description = description;
        return this;
    }

    public GameBuilder price(int price) {
        this.price = price;
        return this;
    }

    public GameBuilder stock(int stock){
        this.stock = stock;
        return this;
    }

    public Game build() {
        if (this.id == null){
            this.id = UUID.randomUUID().toString();
        }
        Game game = new Game();
        game.setId(this.id);
        game.setName(this.name);
        game.setDescription(this.description);
        game.setPrice(this.price);
        game.setStock(this.stock);
        return game;
    }
}

//class Solution{
//    public
//}