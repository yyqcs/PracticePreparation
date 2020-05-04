# https://leetcode-cn.com/problems/game-play-analysis-ii/
#
# Table: Activity
#
# +--------------+---------+
# | Column Name  | Type    |
# +--------------+---------+
# | player_id    | int     |
# | device_id    | int     |
# | event_date   | date    |
# | games_played | int     |
# +--------------+---------+
#
# (player_id, event_date) 是这个表的两个主键
# 这个表显示的是某些游戏玩家的游戏活动情况
# 每一行是在某天使用某个设备登出之前登录并玩多个游戏（可能为0）的玩家的记录
# 请编写一个 SQL 查询，描述每一个玩家首次登陆的设备名称
#
# 查询结果格式在以下示例中：
#
# Activity table:
#
# +-----------+-----------+------------+--------------+
# | player_id | device_id | event_date | games_played |
# +-----------+-----------+------------+--------------+
# | 1         | 2         | 2016-03-01 | 5            |
# | 1         | 2         | 2016-05-02 | 6            |
# | 2         | 3         | 2017-06-25 | 1            |
# | 3         | 1         | 2016-03-02 | 0            |
# | 3         | 4         | 2018-07-03 | 5            |
# +-----------+-----------+------------+--------------+
#
# Result table:
#
# +-----------+-----------+
# | player_id | device_id |
# +-----------+-----------+
# | 1         | 2         |
# | 2         | 3         |
# | 3         | 1         |
# +-----------+-----------+

-- 首次时间，作为筛选添加，选择别的
## 思考：与I不同的地方是首次登陆的设备名称,此时需要用首次登陆时间作为筛选条件，选取其中的first_date；
## 右内连接,首次，作为连接条件的！！
## 体会筛选条件，ON。

USE  leetcode;

SELECT a.player_id ,a.device_id
FROM Activity as a RIGHT JOIN (
    SELECT player_id ,MIN(event_date) as first_date
    FROM Activity
    GROUP BY player_id
    ) as b
ON a.event_date=b.first_date -- 首次
where a.player_id=b.player_id;
