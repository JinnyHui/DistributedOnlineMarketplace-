JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES =AbstractFactory.java \
		ActionInterface.java \
		ActionServerController.java \
		ActionServerModel.java \
		AdminFactory.java \
		AdminMenu.java \
		AuthorizationException.java \
		AuthorizationInvocationHandler.java \
		Client.java \
		ClientController.java \
		Credential.java \
		CustomerFactory.java \
		CustomerMenu.java \
		Dispatcher.java \
		FrontController.java \
		Inventory.java \
		LoginView.java \
		MarketplaceLogin.java \
		MenuView.java \
		MySQL.java \
		Product.java \
		Request.java \
		RequiresRole.java \
		Response.java \
		Server.java \
		ServerLoginController.java \
		ServerLoginModel.java \
		Session.java \
		ShoppingCart.java
default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class