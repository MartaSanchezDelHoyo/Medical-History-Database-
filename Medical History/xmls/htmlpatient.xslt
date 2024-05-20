<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/Patient">
        <html>
            <head>
                <title>Patient Info</title>
            </head>
            <body>
                <h1>Patient Information</h1>
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
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
