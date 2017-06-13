package com.bibabo.framework.glide;

import android.content.Context;

import com.bibabo.framework.config.ShowConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * 缓存设置
 * <p>
 * 在GlideModule 中,我们可以设置磁盘缓存的位置,磁盘缓存的大小和内存缓存的大小,同时还可以设置图片的显示质量.
 * 要是用GlideModule ,需要创建它的实现类,然后在manifests中申明实现类的全类路径:
 * 所以你知道要创建一个额外的类去定制 Glide。
 * 下一步是要全局的去声明这个类，让 Glide 知道它应该在哪里被加载和使用。
 * Glide 会扫描 AndroidManifest.xml 为 Glide module 的 meta 声明。
 * 因此，你必须在 AndroidManifest.xml 的 <application> 标签内去声明这个SimpleGlideModule。
 * Created by mChenys on 2016/6/10.
 */
public class SimpleGlideModule implements GlideModule {
    public static DiskCache cache;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 在 Android 中有两个主要的方法对图片进行解码：ARGB8888 和 RGB565。前者为每个像素使用了 4 个字节，
        // 后者仅为每个像素使用了 2 个字节。ARGB8888 的优势是图像质量更高以及能存储一个 alpha 通道。
        // Picasso 使用 ARGB8888，Glide 默认使用低质量的 RGB565。
        // 对于 Glide 使用者来说：你使用 Glide module 方法去改变解码规则。
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置缓存目录
        File cacheDir = new File(ShowConfig.getImageCacheFolderPath());

        cache = DiskLruCacheWrapper.get(cacheDir, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);// 250 MB
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                return cache;
            }
        });
        //设置memory和Bitmap池的大小
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}