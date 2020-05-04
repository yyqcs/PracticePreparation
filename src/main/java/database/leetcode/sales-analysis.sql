# 1082	 销售分析 I
# 编写一个 SQL 查询，查询总销售额最高的销售者，如果有并列的，就都展示出来。



-- 思路：查出所有seller的销售额，然后查大于等于所有销售额的selleer。,MAX的含义。
SELECT seller_id
FROM sales
group by seller_id
HAVING SUM(price)>=all(SELECT SUM(price)
                        FROM  sales GROUP BY seller_id);

-- II.查询购买了 S8 手机(in )却没有购买(not in) iPhone 的买家
# 查出两个手机的id，根据手机id查出买过两个手机的名单，判断每一个人在第一个名单里，不爱第二个名单里。

SELECT DISTINCT buyer_id
FROM Sales
WHERE buyer_id in( SELECT buyer_id FROM Sales
                    WHERE product_id=(SELECT produce_id FROM Product where product_name='S8'))


AND
      buyer_id NOT IN( SELECT buyer_id FROM Sales
                       WHERE product_id=( SELECT produce_id FROM Product where product_name='iPhone'))



-- III .编写一个SQL查询，报告2019年春季才售出的产品。即仅在2019-01-01至2019-03-31（含）之间出售的商品。

-- 思路：ID要排除的。
SELECT product_id ,product_name
FROM Product
WHERE product_id not in(select product_id
                        FROM Sales
                        WHERE sale_date>'2019-03-31' or sale_date <sale_date<'2019-01-01');