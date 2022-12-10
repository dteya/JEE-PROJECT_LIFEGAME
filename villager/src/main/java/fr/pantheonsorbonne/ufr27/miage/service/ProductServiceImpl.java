package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BankAccountDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.InventoryDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.VillagerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.WishlistDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Merchandise;
import fr.pantheonsorbonne.ufr27.miage.dto.Product;
import fr.pantheonsorbonne.ufr27.miage.model.Villager;
import fr.pantheonsorbonne.ufr27.miage.model.Wishlist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    BankingService bankingService;

    @Inject
    InventoryService inventoryService;

    @Inject
    WishlistDAO wishlistDAO;

    @Inject
    VillagerDAO villagerDAO;

    @Inject
    InventoryDAO inventoryDAO;

    @Inject
    HousingService housingService;

    @Override
    public boolean findLikedProduct(Product product, int idVillager) {
        Villager villager = villagerDAO.getVillager(idVillager);
        if (inventoryService.sufficientSpace(idVillager)) {
            Wishlist wishlist = wishlistDAO.getWishlist(idVillager);
            System.out.println("Villager" + idVillager + " has enough space");
            if (wishlist.getShape().equals(product.getShape()) && Objects.equals(wishlist.getColor(), product.getColor()) && villager.getLevel() == product.getLevel()) {
                System.out.println("Villager " + idVillager + " likes this " + product.getName());
                if (bankingService.sufficientBalance(product.getPrice(), idVillager)) {
                    System.out.println("Villager " + idVillager + " has enough money to purchase this item");
                    return true;
                }
                else { System.out.println("Villager " + idVillager + " doesn't have enough money for purchase"); }
            }
            else { System.out.println("Villager " + idVillager + " doesn't like this" + product.getName()); }
        }
        else {
            System.out.println("Villager " + idVillager + " doesn't have enough space to purchase an item");
            Boolean res = housingService.upgradeHouse(idVillager);
            if (res = false)
                System.out.println("Villager " + idVillager + " doesn't have enough money for purchase");

        }
        return false;
    }

    @Override
    public void purchaseProducts(Merchandise merchandise, int idVillager) {
        for (Product product : merchandise.getMerchandise()) {
            inventoryDAO.addToInventory(idVillager);
            bankingService.debitBankAccount(product.getPrice(), idVillager);
        }
    }

    @Override
    public Merchandise scavengeMerchandise(Merchandise merchandise, int idVillager) {
        Collection<Product> likedProducts = new ArrayList<>();
        for(Product product : merchandise.getMerchandise()) {
            if(this.findLikedProduct(product, idVillager)) {
                likedProducts.add(product);
            }
        }
        if (likedProducts.size() == 0) {return null;}
        return new Merchandise(likedProducts);
    }

}
