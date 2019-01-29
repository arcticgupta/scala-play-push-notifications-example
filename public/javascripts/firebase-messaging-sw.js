importScripts('https://www.gstatic.com/firebasejs/3.9.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/3.9.0/firebase-messaging.js');


firebase.initializeApp({
    'messagingSenderId': '949281262581'
});


const messaging = firebase.messaging();


messaging.setBackgroundMessageHandler(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    const notificationTitle = 'Answer to some things';
    const notificationOptions = {
        body: 'This is not the answer to life, universe and everything that comes in the middle..',
    };

    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});