package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.SupplierDAO;
import lk.ijse.deb.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE  FROM  supplier WHERE  supplierId=?",id);
    }

    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",supplier.getSupplierId(),supplier.getSupplierName(),supplier.getContactNumber(),supplier.getEmail());
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE supplier SET  supplierName= ?, contactNumber = ?, email  = ? WHERE supplierId = ?",supplier.getSupplierName(),supplier.getContactNumber(),supplier.getEmail(),supplier.getSupplierId());
    }

    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE supplierId  = ?",id);
        rst.next();
        return new Supplier(rst.getString("supplierId"),rst.getString("supplierName"),rst.getString("contactNumber"),rst.getString("email"));

    }

    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from supplier");
        List<Supplier> supplierList = new ArrayList<>();

        while (rst.next()) {
            Supplier supplier = new Supplier(rst.getString("supplierId"),rst.getString("supplierName"),rst.getString("contactNumber"),rst.getString("email"));

       supplierList.add(supplier);
        }
        return supplierList;
    }
}
