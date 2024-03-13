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

    func makeUIViewController(context: Context) -> UIViewController {
        MainScreen_iosKt.MainScreenController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
