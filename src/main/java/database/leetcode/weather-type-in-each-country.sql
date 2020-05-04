-- 1294	不同国家的天气类型
SELECT country_name,
       case when AVG(weather_state)<=15 then 'Cold'
            when AVG(weather_state)<=25 then 'Hot'
            else 'Warm'
       END AS weather_type
FROM countries AS c INNER JOIN weather AS w
ON c.country_id=w.country_id
WHERE `day` BETWEEN '2019-11-01' AND '2019-11-30'
group by country_name;

-- 	1303	 求团队人数  ,子连接与连接条件应该是你所希望保留的元组的条件。
SELECT e1.employee_id,COUNT(*) AS team_size
FROM employee AS e1 LEFT OUTER JOIN  employee AS e2
ON e1.team_id=e2.team_id # 联想到求工资高于经理的员工。
GROUP BY e1.employee_id;

-- SQL-1322. 广告效果。
# IFNULL的应用，或者IF，可以直接求和的。
SELECT ad_id, IFNULL(ROUND(SUM(action='Clicked')/
(SUM(action='Clicked')+SUM(action='Viewed'))*100,2),0) AS ctr
FROM Ads
GROUP BY ad_id
ORDER BY ctr DESC ,ad_id;
