import SwiftUI
import shared
@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            ZStack {
                MainScreen()
                    .ignoresSafeArea(.all)
            }
		}
	}
}

struct MainScreen: UIViewControllerRepresentable {
init() {
listenForKeyboardNotifications()
}
    func makeUIViewController(context: Context) -> UIViewController {
        MainScreen_iosKt.MainScreenController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

private func listenForKeyboardNotifications() {
    NotificationCenter.default.addObserver(forName: UIResponder.keyboardDidShowNotification,
                                           object: nil,
                                           queue: .main) { (notification) in
                                            guard let userInfo = notification.userInfo,
                                            let keyboardRect = userInfo[UIResponder.keyboardFrameEndUserInfoKey] as? CGRect else { return }
                                            let keyboardHeight = keyboardRect.height
                                            print("keyboardHeight: \(keyboardHeight)")
    }
    NotificationCenter.default.addObserver(forName: UIResponder.keyboardDidHideNotification,
                                           object: nil,
                                           queue: .main) { (notification) in
                                            let keyboardHeight = 0
                                           print("keyboardHeight: \(keyboardHeight)")
    }
}