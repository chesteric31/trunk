package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Command detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "COMMAND_DETAIL")
public class CommandDetail extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3749700047686967069L;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vatApplicable;
    private BigDecimal discountRate;
    private boolean transferred;

    private Command command;
    private Product product;

    /**
     * Constructor.
     */
    public CommandDetail() {
        super();
    }

    /**
     * 
     * @return the quantity
     */
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return the price
     */
    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 
     * @return the VAT to apply
     */
    @Column(name = "VAT_APPLICABLE")
    public BigDecimal getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable
     *            the VAT applicable to set
     */
    public void setVatApplicable(BigDecimal vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return a discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate
     *            a discount rate to set
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return true if the {@link CommandDetail} was transferred to a bill detail, false otherwise
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred
     *            the transferred boolean to set (true, this {@link CommandDetail} is transferred to a bill detail,
     *            false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link Command}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_COMMAND")
    public Command getCommand() {
        return command;
    }

    /**
     * 
     * @param command
     *            the {@link Command} to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 
     * @return the {@link Product}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PRODUCT")
    public Product getProduct() {
        return product;
    }

    /**
     * 
     * @param product
     *            the {@link Product} to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CommandDetail [command=" + command + ", discountRate=" + discountRate + ", price=" + price
                + ", product=" + product + ", quantity=" + quantity + ", transferred=" + transferred
                + ", vatApplicable=" + vatApplicable + "]";
    }

}
