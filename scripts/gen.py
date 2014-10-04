url = "http://localhost:8080/costs"
import json
import requests
from random import randrange
from faker import Faker
import threading
fake = Faker()

threads = 10




def create_data():
    for n in range(0,5):
        payload = {"amount": randrange(102020020202), "description":fake.name()}
        headers = {"Content-Type":"application/json"}
        requests.post(url,data=json.dumps(payload),headers=headers)


for n in range(0,200):
    t = threading.Thread(target=create_data)
    t.daemon = False
    t.start()