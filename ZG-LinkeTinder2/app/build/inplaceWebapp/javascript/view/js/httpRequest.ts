import {Person} from "./IPerson"

function sendPostToServer(url: URL, body: Person) {

    let request = new XMLHttpRequest()

    request.open("POST", url, true)
    request.setRequestHeader("Content-Type", "signup/json")
    request.send(JSON.stringify(body))


    request.onload = () => {
        console.log(request.responseText)
    }

    return request.responseText
}

export {sendPostToServer};