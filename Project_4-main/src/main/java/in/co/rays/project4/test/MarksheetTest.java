package in.co.rays.project4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project4.bean.MarksheetBean;
import in.co.rays.project4.exception.ApplicationException;
import in.co.rays.project4.exception.DuplicateRecordException;
import in.co.rays.project4.model.MarksheetModel;


/**
 * For marksheet model testing 
 * @author Lalit Kewat
 *
 */
public class MarksheetTest {

	/**
	 * Model object to test
	 */

	public static MarksheetModel model = new MarksheetModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
	//	testAdd();
	//	testDelete();
	//	testUpdate();
	//	testFindByRollNo();
	//	testFindByPK();
		testSearch();
//		testList();
		//testMeritList();
	}

	/**
	 * Tests add a Marksheet
	 */

	public static void testAdd() {

		try {
			MarksheetBean bean = new MarksheetBean();
			// bean.setId(1L);
			bean.setRollNo("99");
			bean.setPhysics(88);
			bean.setChemistry(77);
			bean.setMaths(99);
			bean.setStudentId(4);
			long pk = model.add(bean);
			System.out.println("Add Marksheet Test");
			MarksheetBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a marksheet by PK.
	 */
	public static void testFindByPK() {
		try {
			MarksheetBean bean = new MarksheetBean();
			long pk = 4;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}	
	/**
	     * Tests delete a Marksheet
	     */
	    public static void testDelete() {

	        try {
	            MarksheetBean bean = new MarksheetBean();
	            long pk = 5;
	            bean.setId(pk);
	            model.delete(bean);
	            MarksheetBean deletedbean = model.findByPK(pk);
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Tests update a Marksheet
	     */
	    public static void testUpdate() {

	        try {
	            MarksheetBean bean = model.findByPK(4);
	            bean.setName("Medicaps");
	            bean.setChemistry(57);
	            bean.setMaths(77);
	            // bean.setStudentId(2);
	            model.update(bean);

	            MarksheetBean updatedbean = model.findByPK(4);
	            System.out.println("Test Update succ");
	            if (!"ram kumawat".equals(updatedbean.getName())) {
	                System.out.println("Test Update fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    /**
	     * Tests find a marksheet by Roll No.
	     */

	    public static void testFindByRollNo() {

	        try {
	            MarksheetBean bean = model.findByRollNo("100");
	            if (bean == null) {
	                System.out.println("Test Find By RollNo fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getRollNo());
	            System.out.println(bean.getName());
	            System.out.println(bean.getPhysics());
	            System.out.println(bean.getChemistry());
	            System.out.println(bean.getMaths());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Tests search a Marksheets
	     */

	    public static void testSearch() {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            bean.setName("SHUBHAM SHARMA");
	         //   bean.setRollNo("108");
	            list = model.search(bean, 1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getRollNo());
	                System.out.println(bean.getName());
	                System.out.println(bean.getPhysics());
	                System.out.println(bean.getChemistry());
	                System.out.println(bean.getMaths());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Tests list of Marksheets
	     */
	    public static void testList() {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            list = model.list(1, 6);
	            if (list.size() < 0) {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getRollNo());
	                System.out.println(bean.getName());
	                System.out.println(bean.getPhysics());
	                System.out.println(bean.getChemistry());
	                System.out.println(bean.getMaths());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDateTime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDateTime());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    /**
	     * Tests get the meritlist of Marksheets
	     */
	    public static void testMeritList() {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            list = model.getMeritList(1, 5);
	            if (list.size() < 0) {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getRollNo());
	                System.out.println(bean.getName());
	                System.out.println(bean.getPhysics());
	                System.out.println(bean.getChemistry());
	                System.out.println(bean.getMaths());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }

	}
	    

	
	
	

