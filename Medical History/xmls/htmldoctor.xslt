<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/Doctors">
        <html>
            <head>
                <meta http-equiv="content-type" content="text/html; charset=utf-8" />
                <meta http-equiv="content-language" content="cs" />
                <meta name="robots" content="all,follow" />
                <meta name="author" content="All: ... [Nazev webu - www.url.cz]; e-mail: info@url.cz" />
                <meta name="copyright" content="Design/Code: Vit Dlouhy [Nuvio - www.nuvio.cz]; e-mail: vit.dlouhy@nuvio.cz" />
                <title>Your Health Journey</title>
                <meta name="description" content="..." />
                <meta name="keywords" content="..." />
                <link rel="index" href="./" title="Home" />
                <link rel="stylesheet" media="screen,projection" type="text/css" href="./css/main.css" />
                <link rel="stylesheet" media="print" type="text/css" href="./css/print.css" />
                <link rel="stylesheet" media="aural" type="text/css" href="./css/aural.css" />
            </head>

            <body id="www-url-cz">
                <!-- Main -->
                <div id="main" class="box">
                    <!-- Header -->
                    <div id="header">
                        <!-- Logotyp -->
                        <h1 id="logo"><a href="./" title="CrystalX [Go to homepage]">Your <strong>Health Journey</strong><span></span></a></h1>
                        <hr class="noscreen" />

                        <!-- Quick links -->
                        <div class="noscreen noprint">
                            <p><em>Quick links: <a href="#content">content</a>, <a href="#tabs">navigation</a>, <a href="#search">search</a>.</em></p>
                            <hr />
                        </div>

                        <!-- Search -->
                        <div id="search" class="noprint">
                            <form action="" method="get">
                                <fieldset>
                                    <legend>Search</legend>
                                    <label><span class="noscreen">Find:</span>
                                    <span id="search-input-out"><input type="text" name="" id="search-input" size="30" /></span></label>
                                    <input type="image" src="design/search_submit.gif" id="search-submit" value="OK" />
                                </fieldset>
                            </form>
                        </div> <!-- /search -->
                    </div> <!-- /header -->

                    <!-- Main menu (tabs) -->
                    <div id="tabs" class="noprint">
                        <h3 class="noscreen">Navigation</h3>
                        <ul class="box">
                            <li id="active"><a href="#">Weblog<span class="tab-l"></span><span class="tab-r"></span></a></li> <!-- Active -->
                        </ul>
                        <hr class="noscreen" />
                    </div> <!-- /tabs -->

                    <!-- Page (2 columns) -->
                    <div id="page" class="box">
                        <div id="strip" class="box noprint">
                            <!-- Breadcrumbs -->
                            <p id="breadcrumbs">You are here: <a href="#">Home</a> &gt; <a href="#">Category</a> &gt; <strong>Page</strong></p>
                            <hr class="noscreen" />
                        </div> <!-- /strip -->

                        <!-- Content -->
                        <div id="content">
                            <!-- Article -->
                            <xsl:for-each select="Doctor">
                                <div class="article">
                                    <h2><span><a href="#">Doctor Info</a></span></h2>
                                    <table>
                                        <tr><th>Doctor ID</th><td><xsl:value-of select="@doctor_id" /></td></tr>
                                        <tr><th>Contact</th><td><xsl:value-of select="@contact" /></td></tr>
                                        <tr><th>Username</th><td><xsl:value-of select="@username" /></td></tr>
                                        <tr><th>Name</th><td><xsl:value-of select="name" /></td></tr>
                                        <tr><th>Surname</th><td><xsl:value-of select="surname" /></td></tr>
                                        <tr><th>Specialty</th><td><xsl:value-of select="specialty" /></td></tr>
                                    </table>
                                    <h2>Hospitals</h2>
                                    <table>
                                        <tr><th>Hospital Name</th><th>Hospital Address</th></tr>
                                        <xsl:for-each select="hospitals/Hospital">
                                            <tr>
                                                <td><xsl:value-of select="hospitalName" /></td>
                                                <td><xsl:value-of select="@hospitalAddress" /></td>
                                            </tr>
                                        </xsl:for-each>
                                    </table>
                                    <h2>Visits</h2>
                                    <table>
                                        <tr><th>Visit Date</th><th>Visit Observation</th></tr>
                                        <xsl:for-each select="visits/Visit">
                                            <tr>
                                                <td><xsl:value-of select="@visit_date" /></td>
                                                <td><xsl:value-of select="@visit_observation" /></td>
                                            </tr>
                                        </xsl:for-each>
                                    </table>
                                    <p class="btn-more box noprint"><strong><a href="#">Continue</a></strong></p>
                                </div> <!-- /article -->
                                <hr class="noscreen" />
                            </xsl:for-each>
                        </div> <!-- /content -->
                    </div> <!-- /page -->
                </div> <!-- /main -->
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
