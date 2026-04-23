package com.macro.mall.portal.service.impl;

import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.output.BooleanListOutput;

class FixedBooleanListOutput<K, V> extends BooleanListOutput<K, V> {
    public FixedBooleanListOutput(RedisCodec<K, V> codec) {
        super(codec);
    }

    // 重点：补齐官方类库漏掉的方法
    @Override
    public void set(boolean value) {
        // 这行代码参考了原版 set(long) 的逻辑
        this.getSubscriber().onNext(this.output, value);
    }
}
