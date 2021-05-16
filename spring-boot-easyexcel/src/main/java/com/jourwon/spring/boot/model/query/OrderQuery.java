package com.jourwon.spring.boot.model.query;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单查询对象
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderQuery-订单查询对象")
public class OrderQuery extends PageQuery implements Serializable {

    private static final long serialVersionUID = 2345100005556267519L;

    /**
     * 开始时间
     */
    private String paymentDateTimeStart;

    /**
     * 结束时间
     */
    private String paymentDateTimeEnd;

    /**
     * 订单状态,0:处理中,1:支付成功,2:支付失败
     */
    private Integer orderStatus;

    /**
     * 是否为当前页,0:当前页,1:全部
     */
    @NotNull(message = "是否为当前页不能为空,0:当前页,1:全部")
    @Range(min = 0, max = 1, message = "是否为当前页0-1")
    private Integer isCurrentPage;

}
