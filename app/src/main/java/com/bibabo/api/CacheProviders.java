package com.bibabo.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * 缓存API接口
 * @LifeCache设置缓存过期时间.
 * 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider, EvictDynamicKey or EvictDynamicKeyGroup .
 * EvictProvider可以明确地清理清理所有缓存数据.
 * EvictDynamicKey可以明确地清理指定的数据 DynamicKey.
 * EvictDynamicKeyGroup 允许明确地清理一组特定的数据. DynamicKeyGroup.
 * DynamicKey驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
 * DynamicKeyGroup。驱逐一组与key关联的数据，使用EvictDynamicKeyGroup。比如分页，排序或筛选要求
 */
public interface CacheProviders {

    //缓存时间 3天
    @LifeCache(duration = 3, timeUnit = TimeUnit.DAYS)
    <T> Flowable<Reply<T>> getCacheData(Flowable<T> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    //缓存时间 4小时
    @LifeCache(duration = 4, timeUnit = TimeUnit.HOURS)
    <T> Flowable<Reply<T>> getCacheDataHours(Flowable<T> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);
}
