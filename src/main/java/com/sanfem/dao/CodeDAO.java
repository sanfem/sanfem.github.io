package com.sanfem.dao;

import com.sanfem.model.Code;
import com.sanfem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码集数据访问对象
 */
public class CodeDAO {

    /**
     * 查询所有代码
     */
    public List<Code> findAll() {
        List<Code> list = new ArrayList<>();
        String sql = "SELECT * FROM codes ORDER BY created_at DESC";
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
     * 根据ID查询代码
     */
    public Code findById(int id) {
        String sql = "SELECT * FROM codes WHERE id = ?";
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
     * 添加代码
     */
    public boolean insert(Code code) {
        String sql = "INSERT INTO codes (title, language, description, content) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code.getTitle());
            ps.setString(2, code.getLanguage());
            ps.setString(3, code.getDescription());
            ps.setString(4, code.getContent());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return false;
    }

    /**
     * 更新代码
     */
    public boolean update(Code code) {
        String sql = "UPDATE codes SET title=?, language=?, description=?, content=? WHERE id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code.getTitle());
            ps.setString(2, code.getLanguage());
            ps.setString(3, code.getDescription());
            ps.setString(4, code.getContent());
            ps.setInt(5, code.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return false;
    }

    /**
     * 删除代码
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM codes WHERE id = ?";
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

    private Code mapRow(ResultSet rs) throws SQLException {
        Code code = new Code();
        code.setId(rs.getInt("id"));
        code.setTitle(rs.getString("title"));
        code.setLanguage(rs.getString("language"));
        code.setDescription(rs.getString("description"));
        code.setContent(rs.getString("content"));
        code.setCreatedAt(rs.getTimestamp("created_at"));
        code.setUpdatedAt(rs.getTimestamp("updated_at"));
        return code;
    }
}
