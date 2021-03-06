package be.jsams.server.model.sale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.server.model.Address;
import be.jsams.server.model.sale.detail.DeliveryOrderDetail;

/**
 * Delivery order entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "DELIVERY_ORDER")
public class DeliveryOrder extends AbstractDocument {

    private Double discountRate;
    private boolean transferred;

    private Address deliveryAddress;

    private List<DeliveryOrderDetail> details;

    /**
     * Constructor.
     */
    public DeliveryOrder() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean the {@link DeliveryOrderBean}
     */
    public DeliveryOrder(DeliveryOrderBean bean) {
        super(bean);
        this.deliveryAddress = new Address(bean.getDeliveryAddress());
        this.discountRate = bean.getDiscountRate();
        this.transferred = bean.isTransferred();
        List<DeliveryOrderDetailBean> list = bean.getDetails();
        List<DeliveryOrderDetail> tmp = new ArrayList<DeliveryOrderDetail>();
        if (list != null) {
            for (DeliveryOrderDetailBean detail : list) {
                tmp.add(new DeliveryOrderDetail(detail, this));
            }
        }
        this.details = tmp;
    }

    /**
     * 
     * @return the discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate the discount rate to set
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return the delivery {@link Address}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_DELIVERY")
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * 
     * @param deliveryAddress the delivery {@link Address} to set
     */
    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 
     * @return a list of {@link DeliveryOrderDetail}
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "deliveryOrder")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<DeliveryOrderDetail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details a list of {@link DeliveryOrderDetail} to set
     */
    public void setDetails(List<DeliveryOrderDetail> details) {
        this.details = details;
    }

    /**
     * @return the transferred
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred the transferred to set
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("DeliveryOrder [deliveryAddress=");
        builder.append(deliveryAddress);
        builder.append(", discountRate=");
        builder.append(discountRate);
        builder.append(", transferred=");
        builder.append(transferred);
        builder.append("]");
        return builder.toString();
    }

}
