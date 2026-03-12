package com.sanfem.dao;

import com.sanfem.model.Photo;
import com.sanfem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 相册数据访问对象
 */
public class PhotoDAO {

    /**
     * 查询所有照片
     */
    public List<Photo> findAll() {
        List<Photo> list = new ArrayList<>();
        String sql = "SELECT * FROM photos ORDER BY created_at DESC";
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
     * 根据ID查询照片
     */
    public Photo findById(int id) {
        String sql = "SELECT * FROM photos WHERE id = ?";
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
     * 添加照片
     */
    public boolean insert(Photo photo) {
        String sql = "INSERT INTO photos (title, description, file_path) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, photo.getTitle());
            ps.setString(2, photo.getDescription());
            ps.setString(3, photo.getFilePath());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return false;
    }

    /**
     * 删除照片
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM photos WHERE id = ?";
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

    private Photo mapRow(ResultSet rs) throws SQLException {
        Photo photo = new Photo();
        photo.setId(rs.getInt("id"));
        photo.setTitle(rs.getString("title"));
        photo.setDescription(rs.getString("description"));
        photo.setFilePath(rs.getString("file_path"));
        photo.setCreatedAt(rs.getTimestamp("created_at"));
        return photo;
    }
}
