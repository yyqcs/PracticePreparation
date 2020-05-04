# 1113	 报告的记录
#查询每种报告理由在昨天的报告数量。

# 日期差
SELECT extra AS report_reason ,COUNT(DISTINCT post_id) AS report_count
FROM actions
WHERE  action='report' AND DATEDIFF('2019-07-05',action_date)=1 AND extra IS NOT NULL
GROUP BY report_reason;

# 1141. 查询近30天活跃用户数

SELECT activity_date AS 'day' ,COUNT(DISTINCT user_id) AS active_users
FROM Activity
where activity_date between '2019-06-28' and '2019-07-27' -- 精确的天数是可以计算的。
GROUP BY activity_date;

-- II 编写SQL查询以查找截至2019年7月27日（含）的30天内每个用户的平均会话数，四舍五入到小数点后两位
SELECT IFNULL(ROUND(COUNT(DISTINCT session_id)/COUNT(DISTINCT user_id),2),0)  AS average_sessions_per_user
FROM Activity
WHERE DATEDIFF('2019-07-27',activity_date)<30;

-- 1148 文章浏览 I

SELECT DISTINCT author_id AS 'id'
FROM Views
WHERE auther_id=viewer_id
ORDER BY auther_id;

-- 	1173 即时食物配送 I
# 求占比，保留4位小数，百分比。

SELECT (ROUND(
        (SELECT COUNT(*) FROM Delivery WHERE order_date=custom_pref_delivery_date)/
        (SELECT COUNT(*) FROM Delivery),4)
        )*100 AS immediate_percentage;

-- 	1211 查询结果的质量和占比

SELECT DISTINCT query_name,ROUND(SUM(rating/postion)/COUNT(query_name),2) AS quality,
                ROUND(SUM(IF(rating<3,1,0))/COUNT(query_name)*100,2) AS poor_query_percentage
FROM Queries
GROUP BY query_name;

-- 1241	 每个帖子的评论数.
SELECT p.post_id,COUNT(DISTINCT (s.sub_id)) AS num_of_comments
FROM Submissions AS s
RIGHT OUTER JOIN (
    SELECT DISTINCT sub_id AS post_id
    FROM Submissions
    WHERE parent_id IS NULL
    ) AS p
ON p.post_id=s.parent_id
GROUP BY p.post_id;

-- 1280	 学生们参加各科测试的次数
SELECT a.student_id ,a.student_name,b.subject_name,COUNT(e.subject_name) AS attend_exams
FROM Students as a CROSS JOIN Subjects AS b LEFT JOIN Examinations AS e
ON a.student_id=e.student_id AND b.subject_name=e.subject_name
GROUP BY a.student_id,b.subject_name
ORDER BY a.student_id,b.subject_name





