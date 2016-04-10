<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Ankith MP2</title>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>" />

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>


        <div class="container"> 

            <!-- Main component for a primary marketing message or call to action -->
            <div class="jumbotron">
                <h1>Ankith Jain Welcome to MP2</h1>
                <p>CRUD FUNCTIONALITY </p>
                <p>The purpose of this project is to explore JSP/JSTL presentation layer technologies, 
                    JDBC database interaction, and the Model-View-Controller (MVC) pattern with Servlets. 
                    JDBC interaction will be C(reate), R(read), U(pdate) and D(elete)</p>

                <p>

                    <a class="btn btn-lg btn-primary" href="/ajain62-MP2/customer" role="button">Create A Customer</a>
                    <a class="btn btn-lg btn-primary" href="/ajain62-MP2/readCustomer" role="button">View All Customers</a>
                    <br/> <br/>
                    <a class="btn btn-lg btn-primary" href="/ajain62-MP2/apidocs/index.html" role="button">View Javadocs</a>


                </p>
            </div>

        </div> <!-- /container -->     

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>

    </body>
</html>
<%@include file="/WEB-INF/jspf/footer.jspf" %>