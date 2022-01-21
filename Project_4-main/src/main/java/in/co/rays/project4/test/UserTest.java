package in.co.rays.project4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project4.bean.UserBean;
import in.co.rays.project4.exception.ApplicationException;
import in.co.rays.project4.exception.DuplicateRecordException;
import in.co.rays.project4.exception.RecordNotFoundException;
import in.co.rays.project4.model.UserModel;


/**
 * for usermodel testing
 * @author Lalit Kewat
 *
 */
public class UserTest {

	/**
	 * Model object to test
	 */

	public static UserModel model = new UserModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 */

	public static void main(String[] args) throws Exception {
//		 testAdd();
		// testDelete();
	//	 testUpdate();
		// testFindByPK();
		// testFindByLogin();
		// testSearch();
	//	 testList();
	//	testAuthenticate();
	//	testGetRoles();
		testchangePassword();   // nhi hui
	//	testRegisterUser();
	//	testresetPassword();   //nhi hui
	//	testforgetPassword();  // nhi hui
	}

	/**
	 * Tests add a User
	 *
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 */
	public static void testAdd() throws ParseException, DuplicateRecordException {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");

			// bean.setId(5234L);
			bean.setFirstName("wertyu");
			bean.setLastName("Tomar");
			bean.setLogin("Rani123@gmail.com");
			bean.setPassword("pass1234");
			// bean.setDob(sdf.parse("31-12-1990"));
			bean.setRoleId(5);
			bean.setMobileNo("345657890");
			bean.setGender("Male");
			// bean.setLastlogin(new Timestamp(new Date().getTime()));
	
			bean.setConfirmPassword("pass1234");
			long pk = model.add(bean);
			UserBean addedbean = model.findByPK(pk);
			System.out.println("Test add succ");
			/*
			 * if (addedbean == null) { System.out.println("Test add fail"); }
			 */
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			UserBean bean = new UserBean();
			long pk = 1;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getConfirmPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a User
	 */
	public static void testDelete() {

		try {
			UserBean bean = new UserBean();
			long pk = 5;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ " + bean.getId());
			UserBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a User
	 */
	public static void testUpdate() {

		try {
			UserBean bean = model.findByPK(1);
			bean.setFirstName("SHUBHAM");
//			bean.setLastName("pvt ltd");
	//			bean.setLogin("ranjit210@gmail.com");
	//		bean.setPassword("658986");

			model.update(bean);

			UserBean updatedbean = model.findByPK(1);
			/*
			 * if (!"".equals(updatedbean.getLogin())) {
			 * System.out.println("Test Update fail"); }
			 */
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by Login.
	 */
	public static void testFindByLogin() {
		try {
			UserBean bean = new UserBean();
			bean = model.findByLogin("ranjit210@gmail.com");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getConfirmPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setFirstName("cmc limited");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getGender());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getGender());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDateTime());
				System.out.println(bean.getModifiedDateTime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests authenticate User.
	 */
	public static void testAuthenticate() {

		try {
			UserBean bean = new UserBean();
			bean.setLogin("shubham123@gmail.com");
			bean.setPassword("12345");
			bean = model.authenticate(bean.getLogin(), bean.getPassword());
			if (bean != null) {
				System.out.println("Successfully login");

			} else {
				System.out.println("Invalid login Id & password");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * Tests get Roles.
     */
    public static void testGetRoles() {

        try {
            UserBean bean = new UserBean();
            List list = new ArrayList();
            bean.setRoleId(7);
            list = model.getRoles(bean);
            if (list.size() < 0) {
                System.out.println("Test Get Roles fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (UserBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getLogin());
                System.out.println(bean.getPassword());
                System.out.println(bean.getDob());
                System.out.println(bean.getRoleId());
                System.out.println(bean.getGender());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests changepassword
     *
     * @throws ParseException
     */
    public static void testchangePassword() {

        try {
            UserBean bean = model.findByLogin("Ryadav123@gmail.com");
            String oldPassword = bean.getPassword();
            bean.setId(1);
            bean.setPassword("7777777");
          //  bean.setConfirmPassword("Bhiya");
            String newPassword = bean.getPassword();
            try {
                model.changePassword(1L, oldPassword, newPassword);
                System.out.println("password has been change successfully");
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Tests add a User register
     *
     * @throws ParseException
     */

    public static void testRegisterUser() throws ParseException {
        try {
            UserBean bean = new UserBean();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            // bean.setId(8L);
            bean.setFirstName("vipin");
            bean.setLastName("kumawat");
            bean.setLogin("navn453@gmail.com");
            bean.setPassword("4444");
            bean.setConfirmPassword("4444");
          //  bean.setDob());
            bean.setGender("Male");
            bean.setRoleId(2);
            long pk = model.registerUser(bean);
            System.out.println("Successfully register");
            System.out.println(bean.getFirstName());
            System.out.println(bean.getLogin());
            System.out.println(bean.getLastName());
            System.out.println(bean.getDob());
            UserBean registerbean = model.findByPK(pk);
            if (registerbean != null) {
                System.out.println("Test registation fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests resetPassword
     *
     * @throws ParseException
     */
    public static void testresetPassword() {
        UserBean bean = new UserBean();
        try {
            bean = model.findByLogin("rathore11@gmail.com");
            if (bean != null) {
                boolean pass = model.resetPassword(bean);
                if (pass = false) {
                    System.out.println("Test Update fail");
                }
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Tests fogetPassword
     *
     * @throws ParseException
     */
    public static void testforgetPassword() {
        try {
            boolean b = model.forgetPassword("Abairagi1@gmail.com");

            System.out.println("Suucess : Test Forget Password Success");

        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}



	

