//
//  AboutListView.swift
//  iosApp
//
//  Created by JonesMbindyo on 29/01/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutListView: View {
    /*
     - 'let' is similar to 'val' in Kt.
     - 'RowItem' is similar to Pairs in Android Kt.
     */
    private struct RowItem: Hashable {
        let title: String
        let subTitle: String
    }
    
    // This constructs the list of items with actual data
    private let items: [RowItem] = {
        let platform = Platform() // instantiate with platform class
        platform.logSystemInfo() // Log
        
        var result : [RowItem] = [
            .init(
                title: "Operating System",
                subTitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device",
                subTitle: platform.deviceModel
            ),
            .init(
                title: "Density",
                subTitle: "@\(platform.density)x"
            )
        ]
        
        return result
    }()
    
    // Here we create a UI: Anything displayable has to extends 'View'
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) { // VStack similar to Column 
                    Text(item.title).font(.footnote).foregroundStyle(.secondary)
                    Text(item.subTitle).font(.body).foregroundStyle(.primary)
                }
                .padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    AboutListView()
}
