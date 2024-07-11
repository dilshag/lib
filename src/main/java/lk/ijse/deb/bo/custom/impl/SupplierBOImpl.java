package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.SupplierDTO;
import lk.ijse.deb.bo.custom.SupplierBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.deb.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAOImpl supplierDAO = (SupplierDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    public boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getContactNumber(),supplier.getEmail()));
    }

    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getContactNumber(),supplier.getEmail()));
    }

    public SupplierDTO searchSupplierId(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getContactNumber(),supplier.getEmail());
    }

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierList = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            SupplierDTO supplierDTO = new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getContactNumber(),supplier.getEmail());

        supplierList.add(supplierDTO);
        }
     return supplierList;
    }
}
