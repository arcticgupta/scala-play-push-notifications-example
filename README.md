# scala-play-push-notifications-example

Required:
1. SBT
2. JDK 11 or higher

Play Framework version 2.6 implemented

Steps to Run:
1. Complete fields to configure your firebase console settings with the code in ```webpush.scala.html``` and ```firebase-messaging-sw.js```. For more information on how to do that, visit official documentation [here](https://firebase.google.com/docs/web/setup).

```
//In webpush.scala.html 
<script>
  // Initialize Firebase
  // TODO: Replace with your project's customized code snippet
  var config = {
    apiKey: "<API_KEY>",
    authDomain: "<PROJECT_ID>.firebaseapp.com",
    databaseURL: "https://<DATABASE_NAME>.firebaseio.com",
    projectId: "<PROJECT_ID>",
    storageBucket: "<BUCKET>.appspot.com",
    messagingSenderId: "<SENDER_ID>",
  };
  firebase.initializeApp(config);
</script>
```

```
//In firebase-messaging-sw.js
firebase.initializeApp({
    'messagingSenderId': '<SENDER_ID>'
});
```

2. ```localhost:9000/webpush``` to register service worker and to generate a token. 
3. Select ```Send Token to Server``` to serve token to the backend.
4. ```localhost:9000/sendNotification``` on a different window. This is required since the generated notification only appears when application is in background. 

