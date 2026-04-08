import SwiftUI

enum AppScreen {
    case login
    case scanner
}

struct RootView: View {
    @State private var currentScreen: AppScreen = .login

    var body: some View {
        Group {
            switch currentScreen {
            case .login:
                LoginView {
                    currentScreen = .scanner
                }
            case .scanner:
                ScannerView {
                    currentScreen = .login
                }
            }
        }
    }
}

#Preview {
    RootView()
}
