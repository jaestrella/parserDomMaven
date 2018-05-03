<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<xsl:template match="/">
	<html>
            <head>
                <meta charset="UTF-8"/>
            </head>
		<body>
                    <h1>TABLA MONDIAL</h1>
                    <table border="1">
			<tr>
                            <th>#</th>
                            <th style="text-align:left">Nombre del pais</th>
                            <th style="text-align:left">Habitantes</th>
                            <th style="text-align:left">Numero de provincias</th>
                            <th style="text-align:left">Capital</th>
                            <th style="text-align:left">Ciudad mas poblada</th>
                            <th style="text-align:left">Continente</th>
			</tr>
			
			<xsl:for-each select="mondial/country">	
			<tr>
                            <xsl:variable name="poblacion" select="max(./descendant::city/population)"/>
                            <td><xsl:value-of select="position()" /></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="sort(population/@year)[last()]/.."/></td>
                            <td><xsl:value-of select="count(province)"/></td>
                            <td><xsl:value-of select="city[@id=../@capital]/name | province/city[@id=../../@capital]/name"/></td>
                            <td>
                                <xsl:value-of select="./descendant::city[population=$poblacion]/name"/>
                                <!--<xsl:if test="string-length(./descendant::city[population=$poblacion]/name) &lt; 1">
                                   Informacion no disponible
                                </xsl:if>-->
                            </td>
                            <td><xsl:value-of select="encompassed/@continent"/></td>
			</tr>	
			</xsl:for-each>			
			</table>
		</body>
		</html>
</xsl:template>
</xsl:stylesheet>
