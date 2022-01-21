package in.co.rays.project4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import in.co.rays.project4.bean.CourseBean;
import in.co.rays.project4.bean.UserBean;
import in.co.rays.project4.exception.ApplicationException;
import in.co.rays.project4.exception.DatabaseException;
import in.co.rays.project4.exception.DuplicateRecordException;
import in.co.rays.project4.util.JDBCDataSource;

/**
 * Find next Primary key of College
 * @author Lalit Kewat
 *
 */
public class CourseModel {
	/** The log. */
	 private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Find next PK of Course.
	 *
	 */

	public Integer nextPk() throws DatabaseException {
		 log.debug("CourseModel nextPK method started");

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Exception in Database..", e);
			throw new DatabaseException("Exception : Exception in getting Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("CourseModel nextPK method END");
		return pk + 1;
	}

	/**
	 * Add a Course.
	 *
	 */

	public int add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		 log.debug("CourseModel Add method END");
		Connection conn = null;
		int pk = 0;

		CourseBean duplicateCourseName = findByName(bean.getcourseName());
		if (duplicateCourseName != null) {
			throw new DuplicateRecordException("Course Name already Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getcourseName());
			pstmt.setString(3, bean.getdescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDateTime());
			pstmt.setTimestamp(7, bean.getModifiedDateTime());
			pstmt.setString(8, bean.getDuration());
			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.debug("EXception in Database...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add Rollback Exception.." + ex.getMessage());
			}
			throw new ApplicationException("Exception in Course Add method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("CourseModel Add method END");
		return pk;
	}
	
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		
		CourseBean beanExist = findByName(bean.getcourseName());
		if(beanExist !=null && beanExist.getId()!=bean.getId()){
			throw new DuplicateRecordException("Course Already Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_COURSE SET COURSE_NAME=?,DISCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,DURATION=?  WHERE ID=?");
			pstmt.setString(1, bean.getcourseName());
			pstmt.setString(2, bean.getdescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDateTime());
			pstmt.setTimestamp(6, bean.getModifiedDateTime());
			pstmt.setString(7, bean.getDuration());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Database Exception ...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Exception in Rollback.." + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception in Updating the Course Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}


	public CourseBean findByName(String name) {

		 log.debug("Course Model FindByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE COURSE_NAME=?");
		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getInt(1));
				bean.setcourseName(rs.getString(2));
				bean.setdescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				bean.setDuration(rs.getString(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.debug("Database Exception ..", e);
	//		 throw new ApplicationException("Exception in Course Model FindByName Method ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Course Model FindByName method End");
		return bean;
	}

	/**
	 * Find User by Course.
	 *
	 */

	public CourseBean findByPk(long pk) throws ApplicationException {
		 log.debug("CourseModel FindbyPK method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getInt(1));
				bean.setcourseName(rs.getString(2));
				bean.setdescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				bean.setDuration(rs.getString(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("DatabaseException ... ", e);
			throw new ApplicationException("Exception : Exception in the findbyPk method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("CourseModel FindbyPK method End");
		return bean;
	}
	
	/**
	 * Delete a Course.
	 *
	 */

	public void delete(CourseBean bean) throws ApplicationException {
		log.debug("CourseModel Delete Method Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_COURSE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Exception in Rollback Method" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delete Method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel Delete Method End");
	}

		/**
	 * Find User by Course
	 * 
	 * 
	 */

	public CourseBean findByPk(Long pk) throws ApplicationException {
		log.debug("CourseModel FindbyPK method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setcourseName(rs.getString(2));
				bean.setdescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				bean.setDuration(rs.getString(8));
			}
			rs.close();
		}catch(Exception e) {
			log.error("DatabaseException ... ", e);
			throw new ApplicationException("Exception : Exception in the findbyPk method");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel FindbyPK method End");
		return bean;
	}

	/**
	 * Search Course
	 * 
	 * 
	 */

	public List search (CourseBean bean) throws ApplicationException{
	return search (bean,0,0);	
	}

	/**
	 * Search Course with pagination
	 * 
	 * 
	 * 
	 */
	
	public List search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
//			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
//				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
//			}
//			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
//				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
//			}
//			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
//				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
//			}
//			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
//				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
//			}
//			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
//				sql.append(" AND DOB = " + bean.getGender());
//			}
//			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
//				sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
//			}
//			if (bean.getRoleId() > 0) {
//				sql.append(" AND ROLEID = " + bean.getRoleId());
//			}
//			if (bean.getGender() != null && bean.getGender().length() > 0) {
//				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
//			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setcourseName(rs.getString(2));
				bean.setdescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				bean.setDuration(rs.getString(8));
				

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	/**
	 * Get List of Course
	 * 
	 * @return list : List of Course
	 * 
	 */
	
	public List list () throws ApplicationException{
		return list(0,0);
	}
	/**
	 * Get List of Course with pagination
	 * 
	 * 
	 */
	
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("CourseModel List Method End");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE ");
	// if page size is greater than zero then apply pagination
		if(pageSize>0){
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit "+ pageNo +" , "+pageSize);
		}
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		System.out.println(sql);
		try{
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt =conn.prepareStatement(sql.toString());
			ResultSet rs =pstmt.executeQuery();
			while (rs.next()) {
				CourseBean bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setcourseName(rs.getString(2));
				bean.setdescription(rs.getString(3));				
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				bean.setDuration(rs.getString(8));
				list.add(bean);
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception in list ...",e);
			throw new ApplicationException("Exception : Exception in CourseModel List method " + e.getMessage());		
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel List Method End");
		return list;
	}
	
}


	


