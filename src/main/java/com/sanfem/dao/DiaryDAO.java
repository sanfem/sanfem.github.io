package com.sanfem.dao;

import com.sanfem.model.Diary;
import com.sanfem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 日记数据访问对象
 */
public class DiaryDAO {

    /**
     * 查询所有日记
     */
    public List<Diary> findAll() {
        List<Diary> list = new ArrayList<>();
        String sql = "SELECT * FROM diaries ORDER BY created_at DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    /**
     * 根据ID查询日记
     */
    public Diary findById(int id) {
        String sql = "SELECT * FROM diaries WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }

    /**
     * 添加日记
     */
    public boolean insert(Diary diary) {
        String sql = "INSERT INTO diaries (title, content, mood, weather) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getContent());
            ps.setString(3, diary.getMood());
            ps.setString(4, diary.getWeather());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return false;
    }

    /**
     * 更新日记
     */
    public boolean update(Diary diary) {
        String sql = "UPDATE diaries SET title=?, content=?, mood=?, weather=? WHERE id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getContent());
            ps.setString(3, diary.getMood());
            ps.setString(4, diary.getWeather());
            ps.setInt(5, diary.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return false;
    }

    /**
     * 删除日记
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM diaries WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return false;
    }

    private Diary mapRow(ResultSet rs) throws SQLException {
        Diary diary = new Diary();
        diary.setId(rs.getInt("id"));
        diary.setTitle(rs.getString("title"));
        diary.setContent(rs.getString("content"));
        diary.setMood(rs.getString("mood"));
        diary.setWeather(rs.getString("weather"));
        diary.setCreatedAt(rs.getTimestamp("created_at"));
        diary.setUpdatedAt(rs.getTimestamp("updated_at"));
        return diary;
    }
}
