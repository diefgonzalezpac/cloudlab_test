import requests

def fetch_superheroes():
    url = 'http://app:8080/api/superheroes'  
    response = requests.get(url)

    if response.status_code == 200:
        superheroes = response.json()
        for hero in superheroes:
            print(f"Name: {hero['name']}")
            print(f"Powers: {', '.join(hero['powers'])}")
            print()
    else:
        print(f"Failed to fetch superheroes, status code: {response.status_code}")

if __name__ == "__main__":
    fetch_superheroes()

