import pandas as pd
import sqlite3

def load_spreadsheets():
    spreadsheet_0 = pd.read_csv('data/shipping_data_0.csv')
    spreadsheet_1 = pd.read_csv('data/shipping_data_1.csv')
    spreadsheet_2 = pd.read_csv('data/shipping_data_2.csv')
    return spreadsheet_0, spreadsheet_1, spreadsheet_2

def insert_spreadsheet_0_data(cursor, data):
    for _, row in data.iterrows():
        cursor.execute(
            """INSERT INTO shipments (shipment_identifier, product_name, quantity, origin, destination) 
            VALUES (?, ?, ?, ?, ?)""",
            (None, row['product'], row['product_quantity'], row['origin_warehouse'], row['destination_store'])
        )

def process_and_insert_spreadsheet_1_and_2(cursor, spreadsheet_1, spreadsheet_2):
    merged_data = pd.merge(spreadsheet_1, spreadsheet_2, on='shipment_identifier')
    for _, row in merged_data.iterrows():
        cursor.execute(
            """INSERT INTO shipments (shipment_identifier, product_name, quantity, origin, destination) 
            VALUES (?, ?, ?, ?, ?)""",
            (row['shipment_identifier'], row['product'], None, row['origin_warehouse'], row['destination_store'])
        )

def create_table(cursor):
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS shipments (
            shipment_identifier TEXT,
            product_name TEXT,
            quantity INTEGER,
            origin TEXT,
            destination TEXT
        )
    ''')

def main():
    spreadsheet_0, spreadsheet_1, spreadsheet_2 = load_spreadsheets()
    conn = sqlite3.connect('shipping_data.db')
    cursor = conn.cursor()
    
    # Create the shipments table if it doesn't exist
    create_table(cursor)
    
    insert_spreadsheet_0_data(cursor, spreadsheet_0)
    process_and_insert_spreadsheet_1_and_2(cursor, spreadsheet_1, spreadsheet_2)
    
    conn.commit()
    conn.close()

if __name__ == "__main__":
    main()
