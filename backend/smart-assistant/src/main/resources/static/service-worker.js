const CACHE_NAME = 'pwa-cache-v1';
const urlsToCache = [
    '/',
    '/index.html',
    '/manifest.json',
    // '/styles.css',
    // '/scripts.js'
];

self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(CACHE_NAME)
            .then(cache => cache.addAll(urlsToCache))
    );
});

self.addEventListener('fetch', event => {
    event.respondWith(
        caches.match(event.request)
            .then(response => response || fetch(event.request))
    );
});

self.addEventListener('push', function(event) {
    const options = {
        body: event.data.text(),
        // icon: '/icon.png',
        // badge: '/badge.png'
    };

    event.waitUntil(
        self.registration.showNotification('푸시 알림', options)
    );
});
