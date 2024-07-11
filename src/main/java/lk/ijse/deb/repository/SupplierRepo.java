package lk.ijse.deb.repository;

public class SupplierRepo {
  /*  public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier  WHERE supplierId  = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<SupplierTm> getAll() throws SQLException {
        String sql = "SELECT * from supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<SupplierTm> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);

            SupplierTm supplier = new SupplierTm(id, name, tel, email);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public static boolean save(SupplierTm supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getTel());
        pstm.setObject(4, supplier.getEmail());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(SupplierTm supplier) throws SQLException {
        String sql = "UPDATE supplier SET  supplierName= ?, contactNumber = ?, email  = ? WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getTel());
        pstm.setObject(3, supplier.getEmail());
        pstm.setObject(4, supplier.getId());

        return pstm.executeUpdate() > 0;
    }


    public static SupplierDTO searchSupplierId(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierId  = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String sid = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);

            SupplierDTO supplier = new SupplierDTO(sid, name, tel, email);



            return supplier;
        }

        return null;
    }

   */
}
