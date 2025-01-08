if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('/service-worker.js')
        .then(function(registration) {
            console.log('Service Worker 등록 성공:', registration);
            requestNotificationPermission();  // 푸시 알림 권한 요청
        })
        .catch(function(error) {
            console.error('Service Worker 등록 실패:', error);
        });
}

// 푸시 알림 권한 요청
function requestNotificationPermission() {
    if ('Notification' in window) {
        // Notification.requestPermission().then(function(permission) {
        //     if (permission === 'granted') {
        //         console.log('푸시 알림 권한 승인');
        //     } else {
        //         console.log('푸시 알림 권한 거부');
        //     }
        // });
        if ('Notification' in window && navigator.serviceWorker) {
            // 사용자 행동 후 알림 권한 요청
            Notification.requestPermission().then(function(permission) {
                if (permission === 'granted') {
                    console.log('알림 권한이 승인되었습니다.');
                    // 알림을 보내는 코드 추가 가능
                } else {
                    console.log('알림 권한이 거부되었습니다.');
                }
            }).catch(function (error) {
                console.error('알림 권한 요청 실패:', error);
            });
        } else {
            console.log('알림 기능을 지원하지 않는 브라우저입니다.');
        }
    }
}

document.getElementById('requestPermissionBtn').addEventListener('click', requestNotificationPermission);