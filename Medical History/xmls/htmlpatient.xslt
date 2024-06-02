<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes"/>
    
    <!-- Match the root element -->
    <xsl:template match="/">
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
                                <fieldset><legend>Search</legend>
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
                            <p id="breadcrumbs">You are here: <a href="#">Home</a> &gt; <a href="#">Category</a> &gt;  <strong>Page</strong></p>
                            <hr class="noscreen" />
                        </div> <!-- /strip -->
                        <!-- Content -->
                        <div id="content">
                            <xsl:for-each select="/Patients/Patient">
                                <!-- Article for each patient -->
                                <div class="article">
                                    <h2><span><a href="#">Patient Info</a></span></h2>
                                    <p><strong>Patient ID:</strong> <xsl:value-of select="@patientID"/></p>
                                    <p><strong>Date of Birth:</strong> <xsl:value-of select="@dateofbirth"/></p>
                                    <p><strong>Blood Type:</strong> <xsl:value-of select="@bloodtype"/></p>
                                    <p><strong>Email:</strong> <xsl:value-of select="@email"/></p>
                                    <p><strong>Username:</strong> <xsl:value-of select="@username"/></p>
                                    <p><strong>Patient Name:</strong> <xsl:value-of select="patientName"/></p>
                                    <h2>Allergies</h2>
                                    <xsl:for-each select="allergies/Allergies">
                                        <p><strong>Allergy ID:</strong> <xsl:value-of select="@allergiesID"/>
                                        - <strong>Allergy Name:</strong> <xsl:value-of select="allergiesName"/></p>
                                    </xsl:for-each>
                                    <h2>Doctors</h2>
                                    <xsl:for-each select="Doctors/Doctor">
                                        <p><strong>Contact:</strong> <xsl:value-of select="@contact"/>
                                        - <strong>Specialty:</strong> <xsl:value-of select="@specialty"/></p>
                                        <p><strong>Name:</strong> <xsl:value-of select="name"/>
                                        <xsl:value-of select="surname"/></p>
                                    </xsl:for-each>
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

