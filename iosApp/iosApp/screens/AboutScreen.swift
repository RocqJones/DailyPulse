//
//  AboutScreen.swift
//  iosApp
//
//  Created by JonesMbindyo on 29/01/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView().navigationTitle("About Device")
        }
    }
}

#Preview {
    AboutScreen()
}
