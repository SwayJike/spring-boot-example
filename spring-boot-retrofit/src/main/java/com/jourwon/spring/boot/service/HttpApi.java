package com.jourwon.spring.boot.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.degrade.Degrade;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy;
import com.github.lianjiatech.retrofit.spring.boot.retry.Retry;
import com.jourwon.spring.boot.model.dto.InsertUserDTO;
import com.jourwon.spring.boot.model.dto.UpdateUserDTO;
import com.jourwon.spring.boot.model.vo.CommonPageVO;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.fallback.HttpApiFallback;
import com.jourwon.spring.boot.service.fallback.HttpDegradeFallbackFactory;
import okhttp3.OkHttpClient;
import retrofit2.http.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 2.2.0 增加熔断器
 * https://github.com/LianjiaTech/retrofit-spring-boot-starter#%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7
 *
 * @author wangyuxi
 * @date 2020/08/24
 * <p>
 * 请求重试
 * retrofit-spring-boot-starter支持请求重试功能，只需要在接口或者方法上加上@Retry注解即可。
 * @Retry支持重试次数maxRetries、重试时间间隔intervalMs以及重试规则retryRules配置。重试规则支持三种配置： RESPONSE_STATUS_NOT_2XX：响应状态码不是2xx时执行重试；
 * OCCUR_IO_EXCEPTION：发生IO异常时执行重试；
 * OCCUR_EXCEPTION：发生任意异常时执行重试；
 * 默认响应状态码不是2xx或者发生IO异常时自动进行重试。需要的话，你也可以继承BaseRetryInterceptor实现自己的请求重试拦截器，然后将其配置上去。
 * <p>
 * <p>
 * logStrategy = LogStrategy.BODY  把请求第三方接口的收到的返回值,打印出日志
 * <p>
 * 请求重试
 * retrofit-spring-boot-starter支持请求重试功能，只需要在接口或者方法上加上@Retry注解即可。
 * @Retry支持重试次数maxRetries、重试时间间隔intervalMs以及重试规则retryRules配置。重试规则支持三种配置： RESPONSE_STATUS_NOT_2XX：响应状态码不是2xx时执行重试；
 * OCCUR_IO_EXCEPTION：发生IO异常时执行重试；
 * OCCUR_EXCEPTION：发生任意异常时执行重试；
 * 默认响应状态码不是2xx或者发生IO异常时自动进行重试。需要的话，你也可以继承BaseRetryInterceptor实现自己的请求重试拦截器，然后将其配置上去。
 * <p>
 * <p>
 * logStrategy = LogStrategy.BODY  把请求第三方接口的收到的返回值,打印出日志
 * <p>
 * 请求重试
 * retrofit-spring-boot-starter支持请求重试功能，只需要在接口或者方法上加上@Retry注解即可。
 * @Retry支持重试次数maxRetries、重试时间间隔intervalMs以及重试规则retryRules配置。重试规则支持三种配置： RESPONSE_STATUS_NOT_2XX：响应状态码不是2xx时执行重试；
 * OCCUR_IO_EXCEPTION：发生IO异常时执行重试；
 * OCCUR_EXCEPTION：发生任意异常时执行重试；
 * 默认响应状态码不是2xx或者发生IO异常时自动进行重试。需要的话，你也可以继承BaseRetryInterceptor实现自己的请求重试拦截器，然后将其配置上去。
 * <p>
 * <p>
 * logStrategy = LogStrategy.BODY  把请求第三方接口的收到的返回值,打印出日志
 */

/*拦截器顺序为标记的顺序，其中全局过滤器排在最后*/

//@Sign
///*2.2.2  @Intercept支持多拦截器配置*/
//@Intercept(handler = TimeStampInterceptor.class, include = {"/api/**"})
//@Intercept(handler = AnotherTimeStampInterceptor.class, include = {"/api/**"})

/**
 * 请求重试
 * retrofit-spring-boot-starter支持请求重试功能，只需要在接口或者方法上加上@Retry注解即可。
 * @Retry支持重试次数maxRetries、重试时间间隔intervalMs以及重试规则retryRules配置。重试规则支持三种配置：
 *
 * RESPONSE_STATUS_NOT_2XX：响应状态码不是2xx时执行重试；
 * OCCUR_IO_EXCEPTION：发生IO异常时执行重试；
 * OCCUR_EXCEPTION：发生任意异常时执行重试；
 * 默认响应状态码不是2xx或者发生IO异常时自动进行重试。需要的话，你也可以继承BaseRetryInterceptor实现自己的请求重试拦截器，然后将其配置上去。
 *
 *
 * logStrategy = LogStrategy.BODY  把请求第三方接口的收到的返回值,打印出日志
 *
 * 默认策略情况下，每5s平均响应时间不得超过500ms，否则启用熔断降级
 *
 */

/**
 * HttpApi
 *
 * @author JourWon
 * @date 2021/2/25
 */
@Retry
@RetrofitClient(baseUrl = "${test.baseUrl}",
        fallback = HttpApiFallback.class,
        fallbackFactory = HttpDegradeFallbackFactory.class,
        logStrategy = LogStrategy.BODY,
        poolName="test1"
)
@Degrade(count = 500)
public interface HttpApi {

    /**
     * 构建出的这个方法一定一定要加  @OkHttpClientBuilder 这个注解
     *
     * @return OkHttpClient.Builder
     */
    @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS);

    }

    @GET("{userId}")
    UserVO getById(@Path("userId") Long userId);

    /**
     * 没有数据路径就加 . 或者 /
     * @return List<UserVO>
     */
    @GET(".")
    List<UserVO> list();

    @GET("page1")
    CommonPageVO<UserVO> page1(@Query("pageNum") Integer pageNum, @Query("pageSize") Integer pageSize, @Query("username") String username, @Query("email") String email, @Query("mobilePhoneNumber") String mobilePhoneNumber);

    @PUT("{userId}")
    boolean updateUser(@Path("userId") Long userId, @Body UpdateUserDTO updateUserDTO);

    @DELETE("{userId}")
    boolean removeById(@Path("userId") Long userId);

    @POST(".")
    boolean insert(@Body InsertUserDTO insertUserDTO);

}