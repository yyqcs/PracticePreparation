# 603.连续空余座位
# https://leetcode-cn.com/problems/consecutive-available-seats/

# 思路：MYSQL,相邻，连续 A-B=1
# 和上升的温度这个题类似
# 日期作为连接条件！

SELECT DISTINCT c1.seat_id
FROM cinema AS c1 INNER JOIN cinema AS c2
ON ABS(c1.seat_id-c2.seat_id)=1
WHERE c1.free=1 AND c2.free=1
ORDER BY  c1.seat_id;

-- 1327 列出指定时间段内所有的下单产品

-- 要求获取在 2020 年 2 月份下单的数量不少于 100 的产品的名字和数目。
SELECT p.product_name,SUM(o.unit) AS unit
FROM products AS p LEFT OUTER JOIN orders AS o
ON p.product_id=o.product_id
WHERE order_time between '2020-02-01' and '2020-02-29'
GROUP BY p.product_name
HAVING SUM(o.unit)>=100
ORDER BY SUM(o.unit);


-- 1350	 院系无效的学生  Not IN 或者连接后取失配元组。
SELECT students.id ,students.name
FROM students LEFT OUTER JOIN department ON student.department_id=department.id
WHERE department.id IS NULL;

-- 	1378	使用唯一标识码替换员工ID
SELECT unique_id,'name'
FROM Employees AS e1 LEFT OUTER JOIN EmployeeUNI AS e2
ON e1.id=e2.id;




