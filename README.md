# Walmart USA's Advanced Software Engineering on Forage

This document provides an overview of four distinct tasks completed, showcasing skills in Python, Java, database design, and software architecture. Below is a detailed breakdown of each task, its implementation, and key highlights.

---

## **Task 1: Power-of-Two Max Heap**

### **Overview**
A power-of-two max heap was implemented in Java for the shipping department to optimize query performance. By leveraging a modified heap structure, the solution achieved a 15% performance boost.

### **Key Highlights**
- Enhanced query performance by 15%.
- Maintained the heap property efficiently in a custom structure.
- Benchmarked the heap to evaluate and optimize its performance.

### **Skills Demonstrated**
- Algorithm design and data structure optimization.
- Performance benchmarking in Java.

---

## **Task 2: UML Class Diagram for Dynamic Data Processor**

### **Overview**
Designed a UML class diagram for a dynamic data processor capable of handling multiple operating modes and connecting to various databases. The architecture adhered to SOLID principles and design patterns.

### **Key Highlights**
- Modeled a reconfigurable architecture for extensibility.
- Applied design patterns for modular and scalable software design.
- Incorporated multiple database connections seamlessly.

### **Skills Demonstrated**
- UML modeling and architecture design.
- Application of SOLID principles and design patterns.
- Software engineering best practices.

---

## **Task 3: Relational Database Design**

### **Overview**
Developed a normalized relational database schema and created an Entity Relationship Diagram (ERD) to represent entities and relationships effectively. This task addressed data redundancy and ensured integrity.

### **Key Highlights**
- Designed a normalized schema to eliminate redundancy.
- Created an ERD for clear visualization of relationships.
- Ensured relational integrity and efficient querying.

### **Skills Demonstrated**
- Relational database design and normalization.
- ERD creation and schema visualization.
- Data integrity and query optimization.

---

## **Task 4: Spreadsheet Data Integration**

### **Overview**
Developed a Python script to extract, transform, and integrate data from multiple spreadsheets into an SQLite database. This solution standardized disparate data formats and facilitated efficient querying.

### **Key Highlights**
- Extracted data from spreadsheets using Pandas.
- Aligned data to fit a unified SQLite schema.
- Automated data insertion for seamless integration.

### **Python Code Snippet**
```python
import pandas as pd
import sqlite3

def load_spreadsheets():
    spreadsheet_0 = pd.read_csv('spreadsheet_0.csv')
    spreadsheet_1 = pd.read_csv('spreadsheet_1.csv')
    spreadsheet_2 = pd.read_csv('spreadsheet_2.csv')
    return spreadsheet_0, spreadsheet_1, spreadsheet_2

def insert_data(cursor, spreadsheet_0, spreadsheet_1, spreadsheet_2):
    for _, row in spreadsheet_0.iterrows():
        cursor.execute(
            """INSERT INTO shipments (shipment_identifier, product_name, quantity, origin, destination) 
            VALUES (?, ?, ?, ?, ?)""",
            (row['shipment_identifier'], row['product_name'], row['quantity'], row['origin'], row['destination'])
        )
    merged_data = pd.merge(spreadsheet_1, spreadsheet_2, on='shipping_identifier')
    for _, row in merged_data.iterrows():
        cursor.execute(
            """INSERT INTO shipments (shipment_identifier, product_name, quantity, origin, destination) 
            VALUES (?, ?, ?, ?, ?)""",
            (row['shipping_identifier'], row['product_name'], row['quantity'], row['origin'], row['destination'])
        )

# Main function
if __name__ == "__main__":
    conn = sqlite3.connect('shipping_data.db')
    cursor = conn.cursor()
    spreadsheet_0, spreadsheet_1, spreadsheet_2 = load_spreadsheets()
    insert_data(cursor, spreadsheet_0, spreadsheet_1, spreadsheet_2)
    conn.commit()
    conn.close()
```

### **Skills Demonstrated**
- Data extraction and transformation using Pandas.
- Database integration with SQLite.
- Python scripting for automated workflows.

---

## **Conclusion**
These tasks collectively demonstrate proficiency in software engineering, database management, and data processing. Each solution was tailored to address specific business requirements, showcasing a robust combination of technical skills and design principles.


---

## **Conclusion**
This program effectively demonstrates end-to-end data processing and integration. By consolidating and structuring shipping data, it enables efficient analysis and querying, addressing the business requirements comprehensively.
