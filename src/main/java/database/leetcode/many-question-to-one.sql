# https://leetcode-cn.com/problems/sales-person
-- CROSS JOIN又称为笛卡尔乘积，实际上是把两个表乘起来。

# 思路1 Not In .思路2:连接后取失配元素

USE leetcode;
SELECT s.name
FROM orders AS o INNER JOIN company AS c
ON o.com_id=c.com_id
RIGHT OUTER JOIN salesperson AS s
ON s.sales_id=o.sales_id AND c.name='RED'
WHERE O.order_id is null; -- 失配元素。

# 610三角形判断，if是case when then else 的一种特例。
select *,if( x+y>z and x+z>y and y+z>x , 'Yes','No') as triangle
from triangle;

#     613 直线上的最近距离
# 注意where条件
SELECT MIN(p1.x-p2.x)
FROM point AS p1, point AS p2
WHERE p1.x>p2.x;

# 619	 只出现一次的最大数字


SELECT num AS 'num'
FROM my_numbers
GROUP BY num
HAVING count(num)=1
ORDER BY num DESC
LIMIT 1;


# 1050	 合作过至少三次的演员和导演

# GROUP BY 可以是多个字段，正如ORDER BY 。。理解：顺序分组。。
SELECT actor_id,director_id
FROM ActorDirector
GROUP BY actor_id,director_id
HAVING count(`timestamp`)>=3;-- 关键则重复时，需要用反括号来标示！！

