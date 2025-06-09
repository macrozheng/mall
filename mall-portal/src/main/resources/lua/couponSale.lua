-- KEYS[1] = couponKey
-- KEYS[2] = userCouponKey
-- ARGV[1] = userId
-- 优惠卷被领完：-1   超过领取限制：-2   成功初次领取：1   成功再次领取：2

-- 获取优惠卷剩余数量
local count = redis.call('HGET', KEYS[1], 'count')
-- 获取每人限制领取人数
local perLimit = tonumber(redis.call('HGET', KEYS[1], 'perLimit'))

-- 检查优惠卷是否被领完
if tonumber(count) <= 0 then
    return -1
end

-- 获取用户已经领取的数量
local userCount = redis.call('HGET', KEYS[2], ARGV[1])

if not userCount then
    -- 用户首次领取
    redis.call('HSET', KEYS[2], ARGV[1], '1')
    -- 优惠卷数量减一
    redis.call('HINCRBY', KEYS[1], 'count', -1)
    return 1
else
    -- 检查是否超过每人限制
    if(tonumber(userCount) < perLimit) then
        -- 增加用户领取数量
        redis.call('HINCRBY', KEYS[2], ARGV[1], 1)
        -- 优惠卷数量减一
        redis.call('HINCRBY', KEYS[1], 'count', -1)
        return 2
    else
        -- 超过领取限制
        return -2
    end
end