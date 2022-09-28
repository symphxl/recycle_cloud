package com.zkhc.recycle_cloud.mapper;

import com.zkhc.recycle_cloud.db.DbOpenHelper;
import com.zkhc.recycle_cloud.entity.PutRecord;

import java.util.ArrayList;
import java.util.List;

public class PutRecordMapper extends DbOpenHelper {


    /**
     * 查询所有记录
     *
     * @return
     */
    public List<PutRecord> getAllStuList() {
        List<PutRecord> list = new ArrayList<>();

        try {
            getConnection();
            String sql = "select * from put_record";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                PutRecord stu2 = new PutRecord();
                list.add(stu2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    /**
     * 添加记录
     *
     * @param po
     * @return
     */
    public int addInfo(PutRecord po) {
        int iRow = 0;
        try {
            getConnection();
            String sql = "insert into put_record(name,address,weight,type,qualified) values (?,?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, po.getName());
            pStmt.setString(2, po.getAddress());
            pStmt.setInt(3, po.getWeight());
            pStmt.setString(4, po.getType());
            pStmt.setLong(5, po.getQualified());
            iRow = pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return iRow;
    }
}
