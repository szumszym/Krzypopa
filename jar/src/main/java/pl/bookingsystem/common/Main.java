package pl.bookingsystem.common;

public class Main {


    public static void main(String[] args) {

        AddToDB.addHotel();
//        AddToDB.addUser();

      /*  ClientDAO dao = new ClientDAOImpl();

        Client dorez =new Client("Chuck", "Norris", "chuckzajebisty@gmail.com", "chucknorris");
        Date data_form= new Date(113,10,13);
        Date data_to = new Date(113,10,15);
        Reservation r =new Reservation("Nowa Rezerwacja",data_form, data_to,10 , new Date(), dorez);
		// Read
		System.out.println("******* READ *******");
		List clients = dao.selectAll(Client.class);
		System.out.println("Total Clients: " + clients.size());
		
		
		// Write
		System.out.println("******* WRITE *******");
        Client client = new Client("Chuck", "Norris", "chuckzajebisty@gmail.com", "chucknorris");
        client = dao.save(client);
		client = dao.selectByID(Client.class, client.getId());
		System.out.printf("%d %s %s \n", client.getId(), client.getFirst_name(), client.getLast_name());

		
		// Update
		System.out.println("******* UPDATE *******");
		Client client2 = dao.selectByID(Client.class, (long) 1); // read client with id 1
		System.out.println("Name Before Update:" + client2.getFirst_name());
		client2.setFirst_name("James");
		dao.merge(client2);	// save the updated client details
		
		client2 = dao.selectByID(Client.class, (long) 1); // read again client with id 1
		System.out.println("Name Aftere Update:" + client2.getFirst_name());
		
		
		// Delete
		System.out.println("******* DELETE *******");
        Client client3 = dao.selectByID(Client.class, client.getId());
        System.out.println("Object:" + client3);
		dao.delete(client);
		client3 = dao.selectByID(Client.class,  client.getId());
		System.out.println("Object:" + client3);*/
        System.exit(0);
    }
}
